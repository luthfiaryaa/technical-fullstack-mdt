import { useState, useContext } from 'react'
import { UserContext } from './UserContext';
import { useNavigate } from 'react-router-dom';
import './App.css'

function App() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const { setUser } = useContext(UserContext);
  const navigate = useNavigate();
  const [errorMessage, setErrorMessage] = useState('');

  const handleLogin = async () => {
    if (!username || !password) {
      setErrorMessage('Username dan password harus diisi');
      return;
      
    }

    try {
      const response = await fetch('http://localhost:8080/api/v1/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          username,
          password,
          secretKey: 'liberate-tutume-ex-inferis-ad-astra-per-aspera',
        }),
      });

      if (!response.ok) {
        throw new Error('Failed to log in');
      }

      const data = await response.json();
      console.log('Login successful:', data);
      setUser({ username }); 
      navigate('/home');
    } catch (error) {
      console.error('Login failed:', error.response ? error.response.data : error.message); 
      setErrorMessage('Invalid username or password.'); 
    }
  };

  return (

    <div>
      <input
        type="text"
        placeholder="Username"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
      />
      <input
        type="password"
        placeholder="Password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />
      <button onClick={handleLogin}>Login</button>
      {errorMessage && <p style={{ color: 'red' }}>{errorMessage}</p>}
    </div>

  )
}

export default App
