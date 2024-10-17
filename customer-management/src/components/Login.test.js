import { render, screen, fireEvent } from '@testing-library/react';
import Login from './Login';
import '@testing-library/jest-dom';

describe('Login Component', () => {
  test('renders login form with email and password inputs', () => {
    render(<Login setToken={jest.fn()} />);

    expect(screen.getByLabelText(/email/i)).toBeInTheDocument();
    expect(screen.getByLabelText(/password/i)).toBeInTheDocument();
    expect(screen.getByRole('button', { name: /login/i })).toBeInTheDocument();
  });

  test('calls setToken on successful login', async () => {
    const setTokenMock = jest.fn();
    render(<Login setToken={setTokenMock} />);

    fireEvent.change(screen.getByLabelText(/email/i), { target: { value: 'test@example.com' } });
    fireEvent.change(screen.getByLabelText(/password/i), { target: { value: 'password123' } });

    fireEvent.click(screen.getByRole('button', { name: /login/i }));

    // Simulate successful API call and response
    await screen.findByText('Logged in successfully');

    expect(setTokenMock).toHaveBeenCalledTimes(1);
  });
});