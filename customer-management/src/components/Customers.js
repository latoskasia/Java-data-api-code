import React, {useState, useEffect} from 'react';
import api from '../api';

const Customers = () => {
    const [customers, setCustomers] = useState([]);
    const [editCustomer, setEditCustomer] = useState(null);
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");


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

    const handleEdit = (customer) => {
        setEditCustomer(customer);
        setName(customer.name);
        setEmail(customer.email);
    }

    const handleSave = async (id) => {
    try{
        await api.put(`/api/customers/${id}`, {name, email});
        setEditCustomer(null);
        const response = await api.get('/api/customers');
        setCustomers(response.data);
    } catch (err) {
        console.error("Error updating customer", err);
    }
    };

    return (
        <div className="customers-container">
            <h2>Customers</h2>
            <ul>
                {customers.map((customer) => (
                  <li key={customer.id}>
                    {editCustomer && editCustomer.id === customer.id ? (
                        <div>
                            <input
                                type="text"
                                value={name}
                                onChange={(e) => setName(e.target.value)}
                            />
                            <input
                                type="email"
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}
                            />
                            <button onClick={() => handleSave(customer.id)}>Save</button>
                        </div>
                        ) : (
                        <div>
                            {customer.name} - {customer.email}
                            <button onClick={() => handleEdit(customer)}>Edit</button>
                        </div>
                    )}
                  </li>
                ))}
            </ul>
        </div>
    );
};
export default Customers;