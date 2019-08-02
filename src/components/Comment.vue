<template>
    <v-form v-model="valid">
    <v-container grid-list-xl>
      <v-layout wrap>
      <v-flex xs2 md1>
        <h3 class="pl-2 pt-3" >{{username}}</h3>
      </v-flex>
        <v-flex xs7 md9>
          <v-text-field
            v-model="content"
            label="Comment Content"
            required
          ></v-text-field>
        </v-flex>
        <v-btn xs1 md2 class="pt-4" color="primary" flat small dark>
            <v-icon>edit</v-icon>
        </v-btn>

      </v-layout>
    </v-container>
  </v-form>
</template>

<script>
import FirebaseService from '@/services/FirebaseService'
import store from '../store.js'

export default {
    name: "Comment",
    data: () => ({
      valid: false,
      content: '',
      username: sessionStorage.getItem('name')
    }),
    methods: {
        writeComment(){

        }
    }
  }
</script>