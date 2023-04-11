<template>
  <div class="container">
    <h1>Contacts</h1>
    <hr />
    <div class="list-group">
      <p v-if="contacts.length <= 0">No contacts...</p>
      <div
        v-else
        class="list-group-item"
        v-for="(contact, index) in allContacts"
        v-bind:key="index"
      >
        <span class="fullName"
          ><strong>{{ contact.firstName }} {{ contact.LastName }}</strong></span
        >
        <div>
          <a href="#" title="Delete" v-on:click.prevent="removeContact(index)"
            >Delete</a
          >
        </div>
      </div>
    </div>
    <hr />
  </div>
</template>

<script>
export default {
  data() {
    return {
      contacts: [],
      firstName: "",
      lastName: "",
      phoneNumber: "",
    };
  },
  methods: {
    addContact(contact) {
      this.contacts.push(contact);
    },
    removeContact(index) {
        this.contacts.splice(index);
    },
  },
  computed: {
    allContacts() {
      return this.contacts.map((contact) => ({
        ...contact,
        firstName: contact.firstName.trim(),
        lastName: contact.lastName.trim(),
        phoneNumber: contact.phoneNumber.trim(),
      }));
    },
  },
};
</script>
