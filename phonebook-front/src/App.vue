<template>
  <div>
    <ContactForm v-on:add-todo="addContact"></ContactForm>
    <ContactList />
  </div>
</template>

<script>
import ContactList from "./components/ContactList.vue";
import ContactForm from "./components/ContactForm.vue";

export default {
  name: "App",
  components: {
    ContactForm,
    ContactList,
  },
};
</script>

<style>
</style>
