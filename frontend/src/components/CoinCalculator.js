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
    <div className="gradient-background">
      <div className="calculator-wrapper">
        <h1>Coin Counter</h1>
        <form onSubmit={handleCalculate} className="form-layout">
          <div className="input-wrapper">
            <label htmlFor="targetAmount">Target Amount</label>
            <input
              type="number"
              id="targetAmount"
              value={targetAmount}
              onChange={(e) => setTargetAmount(e.target.value)}
              placeholder="0.00"
            />
          </div>

          <div className="input-wrapper">
            <label htmlFor="denominations">Denominations</label>
            <input
              type="text"
              id="denominations"
              value={denominations}
              onChange={(e) => setDenominations(e.target.value)}
              placeholder="e.g., 1, 5, 10"
            />
          </div>

          <button type="submit" className="action-button">
            Calculate
          </button>
        </form>

        {result && (
          <div className="result-display">
            <h2>Result</h2>
            <p>{result.join(', ')}</p>
          </div>
        )}

        {error && <div className="error-display">{error}</div>}

        <button
          onClick={() => {
            setTargetAmount('');
            setDenominations('');
            setResult(null);
            setError(null);
          }}
          className="reset-button"
        >
          Reset
        </button>
      </div>
    </div>
  );
};

export default CoinCalculator;
