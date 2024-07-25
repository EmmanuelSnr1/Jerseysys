import axios from 'axios';

const apiClient = axios.create({
  baseURL: '/api', // Base URL now points to the proxied '/api'
  headers: {
    'Content-Type': 'application/json',
  },
});

export default {
  getBooks() {
    return apiClient.get('books/');
  },
  addBook(book) {
    return apiClient.post('books/', book);
  },
};
