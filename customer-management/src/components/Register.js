import React, {useState} from 'react';
import api from '../api';

const Register = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [name, setName] = useState("");

    const handleRegister = async (e) => {
        e.preventDefault();
        try {
            await api.post('http://localhost:8081/account/token', {
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