<template>
  <Navbar />

  <main class="p-4 h-auto mx-auto max-w-screen-lg">

    <h1 class="mb-4 text-3xl font-extrabold text-gray-900 dark:text-white md:text-5xl lg:text-6xl"><span
        class="text-transparent bg-clip-text bg-gradient-to-r to-emerald-600 from-sky-400">Better Data</span> Scalable
      AI.</h1>
    <p class="text-lg font-normal text-gray-500 lg:text-xl dark:text-gray-400">Here at Flowbite we focus on markets
      where technology, innovation, and capital can unlock long-term value and drive economic growth.</p>

    <section id="Table">
      <div class="relative overflow-x-auto">
        <table class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
        <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
          <tr>
            <th scope="col" class="px-6 py-3">ID</th>
            <th scope="col" class="px-6 py-3">Title</th>
            <th scope="col" class="px-6 py-3">Author</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="book in paginatedBooks" :key="book.id" class="bg-white border-b dark:bg-gray-800 dark:border-gray-700">
            <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
              {{ book.id }}
            </th>
            <td class="px-6 py-4">{{ book.title }}</td>
            <td class="px-6 py-4">{{ book.author }}</td>
          </tr>
        </tbody>
      </table>
      </div>
      <nav class="flex items-center flex-column flex-wrap md:flex-row justify-between pt-4" aria-label="Table navigation">
      <span class="text-sm font-normal text-gray-500 dark:text-gray-400 mb-4 md:mb-0 block w-full md:inline md:w-auto">
        Showing <span class="font-semibold text-gray-900 dark:text-white">{{ startItem }}-{{ endItem }}</span> of <span class="font-semibold text-gray-900 dark:text-white">{{ books.length }}</span>
      </span>
      <ul class="inline-flex -space-x-px rtl:space-x-reverse text-sm h-8">
        <li>
          <button @click="prevPage" :disabled="currentPage === 1" class="flex items-center justify-center px-3 h-8 ms-0 leading-tight text-gray-500 bg-white border border-gray-300 rounded-s-lg hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">
            Previous
          </button>
        </li>
        <li v-for="page in totalPages" :key="page">
          <button @click="goToPage(page)" :class="{ 'text-blue-600 bg-blue-50 hover:bg-blue-100 hover:text-blue-700 dark:bg-gray-700 dark:text-white': currentPage === page }" class="flex items-center justify-center px-3 h-8 leading-tight text-gray-500 bg-white border border-gray-300 hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">
            {{ page }}
          </button>
        </li>
        <li>
          <button @click="nextPage" :disabled="currentPage === totalPages" class="flex items-center justify-center px-3 h-8 leading-tight text-gray-500 bg-white border border-gray-300 rounded-e-lg hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">
            Next
          </button>
        </li>
      </ul>
    </nav>



    </section>
  </main>


  <!-- <div>
    <h1>Book List</h1>
    <button @click="fetchBooks">Fetch Books</button>
    <ul>
      <li v-for="book in books" :key="book.id">{{ book.title }}</li>
    </ul>
    <form @submit.prevent="addNewBook">
      <input v-model="newBook.title" placeholder="Book Title" />
      <button type="submit">Add Book</button>
    </form>
  </div> -->


  <Footer />
</template>
  
<script setup>
import Navbar from '../layout/Navbar.vue';

import Footer from '../layout/Footer.vue';
import { useBookStore } from '../../store';
import { ref, onMounted, computed } from 'vue';

// Create a ref for new book
const newBook = ref({ title: '' });

// Access the book store
const bookStore = useBookStore();

const { books, fetchBooks } = bookStore;

const currentPage = ref(1);
const itemsPerPage = 5;

const totalPages = computed(() => Math.ceil(books.length / itemsPerPage));
const paginatedBooks = computed(() => {
  const start = (currentPage.value - 1) * itemsPerPage;
  const end = start + itemsPerPage;
  return books.slice(start, end);
});

const startItem = computed(() => (currentPage.value - 1) * itemsPerPage + 1);
const endItem = computed(() => Math.min(currentPage.value * itemsPerPage, books.length));

const goToPage = (page) => {
  if (page > 0 && page <= totalPages.value) {
    currentPage.value = page;
  }
};

const nextPage = () => {
  if (currentPage.value < totalPages.value) {
    currentPage.value++;
  }
};

const prevPage = () => {
  if (currentPage.value > 1) {
    currentPage.value--;
  }
};

// Fetch books when the component is mounted
onMounted(() => {
  fetchBooks();
  console.log(fetchBooks());
});

</script>
  
<style scoped>
/* Add any additional styling here */
</style>