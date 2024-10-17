import { render, screen } from '@testing-library/react';
import Customers from './Customers';
import '@testing-library/jest-dom';
import api from '../api';
import { act } from 'react-dom/test-utils';

jest.mock('../api');

const mockCustomers = [
  { id: 1, name: 'Kasia', email: 'kasia@gmail.com' },
  { id: 2, name: 'John', email: 'john@gmail.com' },
];

describe('Customers Component', () => {
  test('fetches and displays customers', async () => {
    api.get.mockResolvedValueOnce({ data: mockCustomers });

    await act(async () => {
      render(<Customers />);
    });

    expect(screen.getByText(/Kasia/i)).toBeInTheDocument();
    expect(screen.getByText(/kasia@gmail.com/i)).toBeInTheDocument();
    expect(screen.getByText(/John/i)).toBeInTheDocument();
  });

  test('allows editing a customer', async () => {
    api.get.mockResolvedValueOnce({ data: mockCustomers });

    await act(async () => {
      render(<Customers />);
    });

    fireEvent.click(screen.getAllByText(/edit/i)[0]);

    const nameInput = screen.getByDisplayValue('Kasia');
    fireEvent.change(nameInput, { target: { value: 'Kasia Nowa' } });

    const saveButton = screen.getByText(/save/i);
    fireEvent.click(saveButton);

    expect(screen.getByText(/Kasia Nowa/i)).toBeInTheDocument();
  });
});