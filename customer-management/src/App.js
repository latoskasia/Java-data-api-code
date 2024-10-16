import React, {useState} from 'react';
import Login from './components/Login';
import Customers from './components/Customers';
import Register from './components/Register';
import './App.css';

const App = () => {

  const [token, setToken] = useState(null);

  return (
    <div>
      <h1>Customer Management App</h1>
      {!token ? (
        <>
          <Login setToken={setToken}/>
          <Register />
        </>
      ) : (
        <Customers />
      )}
    </div>
  );
}

export default App;
