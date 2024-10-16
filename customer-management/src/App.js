import React, {useState} from 'react';
import Login from './components/Login';
import Customers from './components/Customers';
import Register from './components/Register';
import './App.css';

const App = () => {

  const [token, setToken] = useState(null);
  const [showRegister, setShowRegister] = useState(false);

  const handleLogout = () => {
    setToken(null);
    localStorage.removeItem('token');
  };

  return (
    <div className="app-container">
      <h1>Customer Management App</h1>
      {!token ? (
        <div>
          <Login setToken={setToken}/>
          <button onClick={()=> setShowRegister(!showRegister)}>
            {showRegister ? 'Hide Registartion' : 'Show Registration'}
          </button>
          {showRegister && <Register />}
        </div>
      ) : (
      <div>
        <button onClick={handleLogout}>Logout</button>
        <Customers />
        </div>
      )}
    </div>
  );
}

export default App;
