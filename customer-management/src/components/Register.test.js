import { render, screen, fireEvent } from '@testing-library/react';
import Register from './Register';
import '@testing-library/jest-dom';

describe('Register Component', () => {
  test('renders registration form', () => {
    render(<Register />);

    expect(screen.getByLabelText(/name/i)).toBeInTheDocument();
    expect(screen.getByLabelText(/email/i)).toBeInTheDocument();
    expect(screen.getByLabelText(/password/i)).toBeInTheDocument();
    expect(screen.getByRole('button', { name: /register/i })).toBeInTheDocument();
  });

  test('shows success message after successful registration', async () => {
    render(<Register />);

    fireEvent.change(screen.getByLabelText(/name/i), { target: { value: 'Kasia' } });
    fireEvent.change(screen.getByLabelText(/email/i), { target: { value: 'kasia@gmail.com' } });
    fireEvent.change(screen.getByLabelText(/password/i), { target: { value: 'password123' } });

    fireEvent.click(screen.getByRole('button', { name: /register/i }));

    // Simulate successful registration
    await screen.findByText('Registered successfully');

    expect(screen.getByText('Registered successfully')).toBeInTheDocument();
  });
});