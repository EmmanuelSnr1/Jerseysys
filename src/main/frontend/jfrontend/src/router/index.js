import { createRouter, createWebHistory } from 'vue-router';
import Dashboard from '../components/pages/Dashboard.vue';
import HelloWorld from '../components/HelloWorld.vue';
import Home from '../components/pages/Home.vue';
import JobManager from '../components/pages/JobManager.vue';



// Import other pages as needed

const routes = [
    {
      path: '/dashboard',
      name: 'Dashboard',
      component: Dashboard,
    },
    {
        path: '/',
        name: 'Home',
        component: Home,
      },
      {
        path: '/jobmanager',
        name: 'JobManager',
        component: JobManager,
      },
    // Add routes for other pages like Jobs and Analytics
  ];
  
  const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes,
  });
  
  export default router;
