<style>

</style>
<template>

</template>

<script>
export default{
    name: 'Admin',
    data () {
        return {
            data: []
        }
    },
    mounted(){
        this.$axios.post('http://192.168.100.90:8000/api/members/getAll')
        .then(response => {
            console.log(response.data[0].name)
            this.data=response.data
            console.log(data)
        });
    }
}
</script>