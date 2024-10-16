import React, {useState} from 'react';
import {accountApi} from '../api';

const Register = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [name, setName] = useState("");

    const handleRegister = async (e) => {
        e.preventDefault();
        console.log("Sending data: ", {name, email, password});
        try {
            await accountApi.post('/register', {
                name,
                email,
                password,
            });
            alert('Registered successfully');
        } catch (err) {
            console.error('Registration failed', err);
            alert('Registration failed');
        }
    };

    return (
        <form onSubmit={handleRegister}>
            <div>
                <label>Name:</label>
                <input type = 'text' value={name} onChange={(e) => setName(e.target.value)}/>
            </div>
            <div>
                <label>Email:</label>
                <input type = 'email' value={email} onChange={(e) => setEmail(e.target.value)}/>
            </div>
            <div>
                <label>Password:</label>
                <input type = 'password' value={password} onChange={(e) => setPassword(e.target.value)}/>
            </div>
            <button type ="submit">Register</button>
        </form>

    );

    };

    export default Register;