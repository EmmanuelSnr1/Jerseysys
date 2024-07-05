<template>
  <div>
    <Navbar />
    <AppNav />

    <main class="p-4 md:ml-64 h-auto pt-20">
      <h1 class="text-2xl font-bold">Dashboard</h1>
      <p class="mt-4">Welcome to the dashboard.</p>
      <ReadSectionObject />
      <ReadSectionObject />
      <ReadSectionObject />

      

      <!-- Add more sections here -->
    </main>
    <Footer />
  </div>
</template>

<script setup>
import Navbar from '../layout/Navbar.vue';
// import AppNav from '../layout/AppNav.vue';
import ReadSectionObject from '../common/ReadSectionObject.vue';


import Footer from '../layout/Footer.vue';
</script>

<style scoped>
/* Add any additional styling here */
</style>
