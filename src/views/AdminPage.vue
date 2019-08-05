<template>
  <div>
    <ImgBanner imgSrc="https://source.unsplash.com/random">
      <div style="margin-bottom:20vw; line-height:1.2em; font-family: 'Righteous', cursive; font-size:calc(30px + 2.5vw)" slot="text">Admin</div>
    </ImgBanner>
    <v-container>
      <Admin></Admin>
    </v-container>
  </div>
</template>

<script>
import ImgBanner from '../components/ImgBanner'
import Admin from '../components/Admin'

export default {
	name: 'AdminPage',
	components: {
        ImgBanner,
        Admin
	}
}
</script>
