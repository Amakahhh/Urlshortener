import React, { useState, useRef } from 'react';
import './style.css';

export default function App() {
  const [longUrl, setLongUrl] = useState('');
  const [shortUrl, setShortUrl] = useState('');
  const shortUrlRef = useRef(null);

  const handlePaste = async () => {
    const text = await navigator.clipboard.readText();
    setLongUrl(text);
  };

  const handleCopy = () => {
    navigator.clipboard.writeText(shortUrl);
  };
  const handleConvert = async () => {
  if (!longUrl.trim()) return alert("Please enter a valid URL");

  try {
    const response = await fetch(`http://localhost:8080/api/url/shorten?longUrl=${encodeURIComponent(longUrl)}`, {
      method: 'POST'
    });

    if (!response.ok) throw new Error('Failed to shorten URL');

    const shortCode = await response.text();
    setShortUrl(`http://localhost:8080/api/url/${shortCode}`);
  } catch (error) {
    alert("Error shortening URL: " + error.message);
  }
};


  return (
    <div className="container responsive-container">
      <div className="card responsive-card">
        <h1 className="title responsive-title">CONVERTER</h1>
        <p className="subtitle responsive-subtitle">Convert any link to a shorter URL</p>

        <div className="input-group responsive-input-group">
          <input
            type="text"
            placeholder="https://www.example.com"
            value={longUrl}
            onChange={(e) => setLongUrl(e.target.value)}
            className="responsive-input"
          />
          <button className="clipboard-btn responsive-clipboard-btn" onClick={handlePaste}>📋 Paste from clipboard</button>
        </div>

        <button className="convert-btn responsive-convert-btn" onClick={handleConvert}>Convert</button>

        <div className="input-group responsive-input-group">
          <input
            type="text"
            value={shortUrl}
            placeholder=""
            readOnly
            ref={shortUrlRef}
            className="responsive-input"
          />
          <button className="clipboard-btn2 responsive-clipboard-btn" onClick={handleCopy}>   <img src="/copyIcon.svg" alt="copy" /> </button>
        </div>

        <footer className="footer responsive-footer">
          
          
        </footer>
      </div>
    </div>
  );
}
