import React, { useState } from 'react';
import './CoinCalculator.css';
import axios from 'axios';

const CoinCalculator = () => {
  const [targetAmount, setTargetAmount] = useState('');
  const [denominations, setDenominations] = useState('');
  const [result, setResult] = useState(null);
  const [error, setError] = useState(null);

  const handleCalculate = async (e) => {
    e.preventDefault();

    const denominationArray = denominations.split(',').map((d) => parseFloat(d.trim()));

    if (isNaN(targetAmount) || targetAmount <= 0 || targetAmount > 10000) {
      setError('Target amount must be a valid number between 0 and 10,000.00');
      return;
    }

    if (denominationArray.some((d) => isNaN(d) || d <= 0)) {
      setError('All denominations must be valid positive numbers.');
      return;
    }

    try {
      const response = await axios.post('http://localhost:8080/api/coins/minimum', {
        targetAmount: parseFloat(targetAmount),
        denominations: denominationArray,
      });

      const sortedResult = response.data.sort((a, b) => a - b);
      setResult(sortedResult);
      setError(null);
    } catch (err) {
      setError('Error calculating coins. Please check your input.');
      setResult(null);
    }
  };

  return (
    <div className="coin-calculator">
      <h1>Coin Calculator</h1>
      <form onSubmit={handleCalculate}>
        <div className="form-group">
          <label>Target Amount:</label>
          <input
            type="text"
            value={targetAmount}
            onChange={(e) => setTargetAmount(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label>Coin Denominations (comma-separated):</label>
          <input
            type="text"
            value={denominations}
            onChange={(e) => setDenominations(e.target.value)}
            required
          />
        </div>
        <button type="submit">Calculate</button>
      </form>

      {result && (
        <div className="result">
          <h3>Result:</h3>
          <p>{result.join(', ')}</p>
        </div>
      )}

      {error && <div className="error">{error}</div>}
    </div>
  );
};

export default CoinCalculator;
