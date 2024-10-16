import React, {useState, useEffect} from 'react';
import api from '../api';

const Customers = () => {
    const [customers, setCustomers] = useState([]);

    useEffect(() => {
        const fetchCustomers = async () => {
            try {
                const response = await api.get('/api/customers');
                setCustomers(response.data);
            } catch (err) {
                console.error('Error fetching customers,', err);
            }
        };
        fetchCustomers();
    },[]);

    return (
        <div>
            <h1>Customers</h1>
            <ul>
                {customers.map((customer) => (
                  <li key={customer.id}>
                    {customer.name} - {customer.email}
                  </li>
                ))}
            </ul>
        </div>
    );
};
export default Customers;