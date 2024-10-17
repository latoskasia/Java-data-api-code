import React, { useState } from 'react';
import { accountApi, setAuthToken } from '../api';

const Login = ({ setToken }) => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await accountApi.post('/token', {
        email,
        password,
      });
      const { token } = response.data;
      setToken(token);
      setAuthToken(token);
      localStorage.setItem('token', token);
      alert('Logged in successfully');
    } catch (err) {
      console.error('Login failed', err);
      alert('Login failed');
    }
  };

  return (
    <form onSubmit={handleLogin}>
      <div>
        <label>Email:</label>
        <input type='email' value={email} onChange={(e) => setEmail(e.target.value)} />
      </div>
      <div>
        <label>Password:</label>
        <input type='password' value={password} onChange={(e) => setPassword(e.target.value)} />
      </div>
      <button type='submit'>Login</button>
    </form>
  );
};

export default Login;