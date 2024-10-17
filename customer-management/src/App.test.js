import { render, screen, fireEvent } from '@testing-library/react';
import App from './App';
import '@testing-library/jest-dom';

describe('App Component', () => {
  test('renders login form when no token is present', () => {
    render(<App />);

    expect(screen.getByText(/customer management app/i)).toBeInTheDocument();
    expect(screen.getByLabelText(/email/i)).toBeInTheDocument();
    expect(screen.getByLabelText(/password/i)).toBeInTheDocument();
  });

  test('shows customers list when logged in', async () => {
    const token = 'sample-token';
    localStorage.setItem('token', token);

    render(<App />);

    expect(screen.getByText(/customers/i)).toBeInTheDocument();
    expect(screen.getByText(/logout/i)).toBeInTheDocument();
  });

  test('logs out when logout button is clicked', async () => {
    const token = 'sample-token';
    localStorage.setItem('token', token);

    render(<App />);

    fireEvent.click(screen.getByText(/logout/i));
    expect(screen.queryByText(/customers/i)).not.toBeInTheDocument();
  });
});