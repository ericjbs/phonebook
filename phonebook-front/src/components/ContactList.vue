<template>
  <div class="container mt-5">
    <h1 class="text-center mb-4">
      <i class="fas fa-address-book mr-2"></i>Phone Book App
    </h1>
    <div v-if="!showAddContactForm && !showUpdateContactForm">
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
              <div @click="toggleUpdateContactForm(contact)">
                <h5 class="mb-1">
                  {{ contact.firstName }} {{ contact.lastName }}
                </h5>
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
    <div v-if="showUpdateContactForm" class="text-center">
      <h4 class="text-center mb-3">Update Contact</h4>
      <form
        @submit.prevent="updateContact(selectedContact.id)"
        class="col-sm-6 col-md-6 col-lg-6 offset-md-3"
      >
        <div class="form-group">
          <input
            v-model="selectedContact.firstName"
            type="text"
            class="form-control"
            id="firstName"
            placeholder="Frist Name"
            required
          />
        </div>
        <div class="form-group">
          <input
            v-model="selectedContact.lastName"
            type="text"
            class="form-control"
            id="lastName"
            placeholder="Last Name"
            required
          />
        </div>
        <div class="form-group">
          <input
            v-model="selectedContact.phone"
            type="text"
            class="form-control"
            id="phone"
            placeholder="Phone"
            required
            pattern="^([0-9]{11})$|^([0-9]{9})$|^(\(?[0-9]{2}\)?)?\s?([9]{1})?\s?([0-9]{4})-?([0-9]{4})"
          />
        </div>
        <button type="submit" class="btn btn-primary">Update</button>
        <button
          type="button"
          class="btn btn-secondary"
          @click="toggleUpdateContactForm"
        >
          Cancel
        </button>
      </form>
    </div>
    <div v-if="showAddContactForm" class="text-center">
      <h4 class="text-center mb-3">Add Contact</h4>
      <form
        @submit.prevent="addContact"
        class="col-sm-6 col-md-6 col-lg-6 offset-md-3"
      >
        <div class="form-group">
          <input
            v-model="newContact.firstName"
            type="text"
            class="form-control"
            id="firstName"
            placeholder="Frist Name"
            required
          />
        </div>
        <div class="form-group">
          <input
            v-model="newContact.lastName"
            type="text"
            class="form-control"
            id="lastName"
            placeholder="Last Name"
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
            pattern="^([0-9]{11})$|^([0-9]{9})$|^(\(?[0-9]{2}\)?)?\s?([9]{1})?\s?([0-9]{4})-?([0-9]{4})"
          />
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
      host: "localhost",
      port: "8080",
      searchTerm: "",
      filteredContacts: [],
      showAddContactForm: false,
      showUpdateContactForm: false,
      newContact: {
        firstName: "",
        lastName: "",
        phone: "",
      },
      selectedContact: {
        id: 0,
        firstName: "",
        lastName: "",
        phone: "",
      },
    };
  },
  mounted() {
    // Fazer requisição à API para buscar todos os contatos
    // Exemplo usando fetch:
    fetch(`http://${this.host}:${this.port}/contacts`)
      .then((response) => response.json())
      .then((data) => {
        this.filteredContacts = data;
      })
      .catch((error) => {
        console.error("Erro ao buscar contatos:", error);
      });
  },
  methods: {
    toggleAddContactForm() {
      this.showAddContactForm = !this.showAddContactForm;
      if (!this.showAddContactForm) {
        this.newContact = {
          firstName: "",
          lastName: "",
          phone: "",
        };
      }
    },
    toggleUpdateContactForm(contact) {
      this.showUpdateContactForm = !this.showUpdateContactForm;
      if (this.showUpdateContactForm) {
        this.selectedContact = {
          id: contact.id,
          firstName: contact.firstName,
          lastName: contact.lastName,
          phone: contact.phone,
        };
      }
    },
    searchContacts() {
      const searchTerm = this.searchTerm.toLowerCase();
      // Fazer requisição à API para buscar os contatos com base no termo de busca
      // Exemplo usando fetch:
      fetch(
        `http://${this.host}:${this.port}/contacts/search?searchTerm=${searchTerm}`
      )
        .then((response) => response.json())
        .then((data) => {
          this.filteredContacts = data;
        })
        .catch((error) => {
          console.error("Erro ao buscar contatos:", error);
        });
    },
    clearSearch() {
      this.searchTerm = ""; // Clear search term
      this.filteredContacts = this.contacts; // Update filtered contacts list to show all contacts
    },
    deleteContact(contactId) {
      // Fazer requisição à API para deletar o contato com o ID fornecido
      // Exemplo usando fetch:
      fetch(`http://${this.host}:${this.port}/contacts/${contactId}`, {
        method: "DELETE",
      })
        .then(() => {
          // Remover o contato da lista de contatos filtrados
          this.filteredContacts = this.filteredContacts.filter(
            (contact) => contact.id !== contactId
          );
        })
        .catch((error) => {
          console.error("Erro ao deletar contato:", error);
        });
    },
    addContact() {
      // Fazer requisição à API para adicionar o novo contato
      // Exemplo usando fetch:
      fetch(`http://${this.host}:${this.port}/contacts`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(this.newContact),
      })
        .then((response) => response.json())
        .then((data) => {
          // Adicionar o novo contato à lista de contatos filtrados e limpar o formulário
          this.filteredContacts.push(data);
          this.toggleAddContactForm();
        })
        .catch((error) => {
          console.error("Erro ao adicionar contato:", error);
        });
    },
    updateContact(contactId) {
      // Fazer requisição à API para adicionar o novo contato
      // Exemplo usando fetch:
      fetch(`http://${this.host}:${this.port}/contacts/${contactId}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(this.selectedContact),
      })
        .then((response) => response.json())
        .then((data) => {
          // Adicionar o novo contato à lista de contatos filtrados e limpar o formulário
          this.filteredContacts = [];
          fetch(`http://${this.host}:${this.port}/contacts`)
            .then((response) => response.json())
            .then((data) => {
              this.filteredContacts = data;
            })
            .catch((error) => {
              console.error("Erro ao buscar contatos:", error);
            });
          
          this.toggleUpdateContactForm(data);
        })
        .catch((error) => {
          console.error("Erro ao adicionar contato:", error);
        });
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