<style>
/* Helper classes */
.basil {
  background-color: #FFFBE6 !important;
}
.basil--text {
  color: #356859 !important;
}
</style>
<template>
<div>
    <v-card color="basil" class="mt-5">
    <v-card-title class="text-center justify-center py-6">
      <h1 class="font-weight-bold display-3 basil--text">Admin Page</h1>
    </v-card-title>

    <v-tabs
      v-model="tab"
      background-color="transparent"
      color="basil"
      grow
    >
      <v-tab @click="show(item)" v-for="item in items" :key="item">
        {{ item }}
      </v-tab>
    </v-tabs>

    <v-tabs-items v-model="tab">
      <v-tab-item v-for="item in items" :key="item">
        <v-card flat color="basil">
          <v-card-text>
              <p v-for="t in text.length" :key="t">
                  {{text[t-1]}}
              </p>
          </v-card-text>
        </v-card>
      </v-tab-item>
    </v-tabs-items>

  </v-card>
</div>
</template>

<script>
export default{
    name: 'Admin',
    data () {
        return {
            data: [],
            tab: null,
            items: [
            'Users', 'Portfolios', 'Posts'
            ],
            text: []
      
        }
    },
    mounted(){
    },
    methods : {
        show(str){
            if(str === "Users")
            {
                this.$axios.post('http://192.168.100.90:8000/api/members/getAll')
                .then(response => {
                    this.data=response.data
                this.text=this.data
                });
            }
            else if(str === "Portfolios")
            {
                this.$axios.post('http://192.168.100.90:8000/api/portfolios/getAll')
                .then(response => {
                    this.data=response.data
                this.text=this.data
                });
            }
            else if(str === "Posts")
            {
                this.text=[]
            }
        }
    }
}
</script>