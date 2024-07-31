import { defineStore } from 'pinia';
import bookService from './service';

export const useBookStore = defineStore('bookStore', {
  state: () => ({
    books: [],
    loading: false,
    error: null,
  }),
  actions: {
    async fetchBooks() {
      this.loading = true;
      try {
        const response = await bookService.getBooks();
        this.books = response.data;
      } catch (error) {
        this.error = error;
      } finally {
        this.loading = false;
      }
    },
    async addBook(book) {
      try {
        const response = await bookService.addBook(book);
        this.books.push(response.data);
        // console.log("The response" ,response)
      } catch (error) {
        this.error = error;
      }
    },
  },
});
