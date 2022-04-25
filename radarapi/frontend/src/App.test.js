import { render, screen } from '@testing-library/react';
import App from './App';

test('renders learn react link', () => {
  render(<App />);
  const linkElement = screen.getByText(/usuarios/i);
  expect(linkElement).toBeInTheDocument();
  const linkElement1 = screen.getByText(/contactos/i);
  expect(linkElement1).toBeInTheDocument();
});
