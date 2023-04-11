<template>
  <div class="container mt-5">
    <h1 class="text-center mb-4">
      <i class="fas fa-address-book mr-2"></i>Phone Book App
    </h1>
    <div v-if="!showAddContactForm">
      <div class="row">
        <div class="col-sm-6 col-md-6 col-lg-6 offset-md-3">
          <div class="d-flex justify-content-between align-items-center">
            <h4 class="text-left mb-3">Contacts</h4>
            <button class="btn btn-primary" @click="toggleAddContactForm">
              + Add Contact
            </button>
          </div>
          <form class="form-inline mb-4 mt-4" @submit.prevent="searchContacts">
            <input
              v-model="searchTerm"
              type="text"
              class="form-control col-sm-12 col-md-12 col-lg-12"
              placeholder="Search for contact by last name"
            />
          </form>
        </div>
      </div>
      <div class="row">
        <div class="col-sm-6 col-md-6 col-lg-6 offset-md-3">
          <ul class="list-group">
            <li
              v-for="contact in filteredContacts"
              :key="contact.id"
              class="list-group-item d-flex justify-content-between align-items-center"
            >
              <div>
                <h5 class="mb-1">{{ contact.name }}</h5>
                <p class="mb-1">{{ contact.phone }}</p>
              </div>
              <div>
                <button
                  @click="deleteContact(contact.id)"
                  class="btn btn-danger btn-md"
                >
                  <i class="fas fa-trash"></i>
                </button>
              </div>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <div v-if="showAddContactForm" class="text-center">
      <h4 class="text-center mb-3">Add Contact</h4>
      <form
        @submit.prevent="addContact"
        class="col-sm-6 col-md-6 col-lg-6 offset-md-3"
      >
        <div class="form-group">
          <input
            v-model="newContact.name"
            type="text"
            class="form-control"
            id="name"
            placeholder="Name"
            required
          />
        </div>
        <div class="form-group">
          <input
            v-model="newContact.email"
            type="email"
            class="form-control"
            id="email"
            placeholder="Email"
            required
          />
        </div>
        <div class="form-group">
          <input
            v-model="newContact.phone"
            type="text"
            class="form-control"
            id="phone"
            placeholder="Phone"
            required
          />
        </div>
        <div class="form-group">
          <textarea
            v-model="newContact.otherInformation"
            class="form-control"
            id="otherInformation"
            rows="3"
            placeholder="Other Information"
          ></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Add</button>
        <button
          type="button"
          class="btn btn-secondary"
          @click="toggleAddContactForm"
        >
          Cancel
        </button>
      </form>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      searchTerm: "",
      filteredContacts: [],
      contacts: [
        {
          id: 1,
          name: "Contact Name 1",
          email: "contact1@example.com",
          phone: "123456789",
          otherInformation: "Other information 1",
        },
        {
          id: 2,
          name: "Contact Name 2",
          email: "contact2@example.com",
          phone: "987654321",
          otherInformation: "Other information 2",
        },
        // Add more contacts here
      ],
      showAddContactForm: false,
      newContact: {
        name: "",
        email: "",
        phone: "",
        otherInformation: "",
      },
    };
  },
  methods: {
    searchContacts() {
      const searchTerm = this.searchTerm.toLowerCase();

      // Filter contacts based on search term
      this.filteredContacts = this.contacts.filter((contact) => {
        return (
          contact.name.toLowerCase().includes(searchTerm) ||
          contact.email.toLowerCase().includes(searchTerm)
        );
      });
    },
    clearSearch() {
      this.searchTerm = ""; // Clear search term
      this.filteredContacts = this.contacts; // Update filtered contacts list to show all contacts
    },
    deleteContact(contactId) {
      this.contacts.splice(contactId);
    },
    toggleAddContactForm() {
      this.showAddContactForm = !this.showAddContactForm;
      if (!this.showAddContactForm) {
        this.newContact = {
          name: "",
          email: "",
          phone: "",
          otherInformation: "",
        };
      }
    },
    addContact() {
      // Add the new contact to the contacts list and clear the form fields.
      this.contacts.push({ ...this.newContact, id: Date.now() });
      this.toggleAddContactForm();
    },
  },
  watch: {
    searchTerm() {
      this.searchContacts();
    },
  },
};
</script>
<style scoped>
/* Bootstrap Styles */
@import url("https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css");
/* Font Awesome Styles */
@import url("https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css");
/* Gray Light Background*/
body {
  background-color: #d8d8d8;
}
</style>