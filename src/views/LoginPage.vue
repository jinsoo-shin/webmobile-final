<style>
.dashed-border {
     width: 100%;
     margin: auto;
     margin-top: 10%;
     margin-bottom: 10%;
     border: 2px dashed #BDBDBD;
}
</style>
<template>
  <v-layout align-center justify-center row fill-height class="mt-2">
    <v-flex hidden-xs-only sm8 lg4 text-xs-center>
      <v-layout md12 justify-center column fill-height elevation-5 style="min-height:500px;" white pa-5>
		<h1 class="mt-4" style="font-family: 'Nanum Gothic Coding', monospace;">LOGIN</h1>
		<v-layout align-center>
			<v-flex text-xs-center>
				<template>
					<form xs12 sm7 md7 v-on:submit='loginWithEmail'>
						<v-text-field type='email' v-model="email" label="E-mail" required></v-text-field>
						<v-text-field type='password' v-model="password" label="Password" required></v-text-field>
						<v-flex md1>
						</v-flex>
						<v-btn xs12 sm4 md4 color="#C0CA33" block type='submit' class="mt-4" @click="loginWithEmail" style="">로그인</v-btn>
					</form>
				</template>
			</v-flex>
		</v-layout>
		<v-flex text-xs-center>
			<v-layout justify-center>
				<v-flex sm6 md6 lg6 class="mt-3 mb-2">
					<v-btn md12 color="#df4a31" dark v-on:click="loginWithGoogle" class="mt-3 mb-4" style="width:90%">
						<v-icon class="mr-4" style="">fa-google</v-icon>구글 로그인
					</v-btn>
				</v-flex>
				<v-flex sm6 md6 lg6 class="mt-3 mb-2 mr-1">
					<v-btn md12 color="#4267b2" dark v-on:click="loginWithFacebook" class="mt-3 mb-4" style="width:90%">
						<v-icon class="mr-4" style="">fa-facebook</v-icon>페이스북 로그인
					</v-btn>
				</v-flex>
			</v-layout>
			<hr class="dashed-border">
			<h4>아직 회원이 아니신가요?</h4>
			<v-layout row justify-center>
				<v-dialog v-model="dialog" persistent max-width="600px">
					<template v-slot:activator="{on}">
						<v-btn class="mt-3" outline v-on="on">회원가입</v-btn>
					</template>
					<v-card>
						<v-card-title>
							<span class="headline">회원가입</span>
						</v-card-title>
						<v-card-text>
							<v-container grid-list-md>
								<v-layout wrap>
									<v-flex xs12 sm6 md4>
										<v-text-field label="Name*" v-model="name" required></v-text-field>
									</v-flex>
									<v-flex xs12>
										<v-text-field label="Email*" v-model="email" required></v-text-field>
									</v-flex>
									<v-flex xs12>
									<v-btn v-on:click="sendEmail">Send</v-btn>
									</v-flex>
									<v-flex xs12>
										<v-text-field label="Authentication*" v-model="auth" required></v-text-field>
									</v-flex>
									<v-flex xs12>
										<v-text-field label="Imgur-Album-Hash*" v-model="album" required></v-text-field>
									</v-flex>
									<v-flex xs12>
										<v-text-field label="Password*" type="password" v-model="password" required></v-text-field>
									</v-flex>
									<v-flex xs12 sm6>
										<v-text-field label="Age*" v-model="age" required></v-text-field>
									</v-flex>
								</v-layout>
							</v-container>
							<small>*indicates required field</small>
						</v-card-text>
						<v-card-actions>
							<v-spacer></v-spacer>
							<v-btn color="blue darken-1" flat @click="dialog = false" v-on:click="close">Close</v-btn>
							<v-btn color="blue darken-1" flat @click="dialog = false" v-on:click="registMember">Regist</v-btn>
						</v-card-actions>
					</v-card>
				</v-dialog>
			</v-layout>
		</v-flex>
      </v-layout>
    </v-flex>
	<v-flex hidden-sm-and-up xs10 sm8 lg4 text-xs-center>
      <v-layout align-center justify-center row fill-height elevation-5 style="min-height:500px;" white pa-5>
        <v-flex xs12 text-xs-center>
			<h1 class="mt-4" style="font-family: 'Nanum Gothic Coding', monospace;">LOGIN</h1>
			<template>
				<form v-on:submit='loginWithEmail'>
					<v-text-field type='email' v-model="email" label="E-mail" required></v-text-field>
					<v-text-field type='password' v-model="password" label="Password" required></v-text-field>
					<v-btn color="#C0CA33" type='submit' style="width:100%;">로그인</v-btn>
				</form>
			</template>
			<br>
			<v-layout justify-space-around>
				<v-btn outline fab medium color="#df4a31" v-on:click="loginWithGoogle">
      				<v-icon size="25" class="ma-2">fa-google</v-icon>
    			</v-btn>
				<v-btn outline fab medium color="#4267b2" v-on:click="loginWithFacebook">
      				<v-icon size="25" class="ma-2">fa-facebook</v-icon>
    			</v-btn>
			</v-layout>
			<hr class="dashed-border">
			<h5 class="mt-3 mb-2">아직 회원이 아니신가요?</h5>
			<v-layout row justify-center>
				<v-dialog v-model="dialog" persistent max-width="600px">
					<template v-slot:activator="{on}">
						<v-btn outline v-on="on">회원가입</v-btn>
					</template>
					<v-card>
						<v-card-title>
						<span class="headline">회원가입</span>
						</v-card-title>
						<v-card-text>
							<v-container grid-list-md>
								<v-layout wrap>
									<v-flex xs12 sm6 md4>
										<v-text-field label="Name*" v-model="name" required></v-text-field>
									</v-flex>
									<v-flex xs12>
										<v-text-field label="Email*" v-model="email" required></v-text-field>
									</v-flex>
									<v-flex xs12>
										<v-btn v-on:click="sendEmail">Send</v-btn>
									</v-flex>
									<v-flex xs12>
										<v-text-field label="Authentication*" v-model="auth" required></v-text-field>
									</v-flex>
									<v-flex xs12>
										<v-text-field label="Imgur-Album-Hash*" v-model="album" required></v-text-field>
									</v-flex>
									<v-flex xs12>
										<v-text-field label="Password*" type="password" v-model="password" required></v-text-field>
									</v-flex>
									<v-flex xs12 sm6>
										<v-text-field label="Age*" v-model="age" required></v-text-field>
									</v-flex>
								</v-layout>
							</v-container>
							<small>*indicates required field</small>
						</v-card-text>
						<v-card-actions>
							<v-spacer></v-spacer>
							<v-btn color="blue darken-1" flat @click="dialog = false" v-on:click="close">Close</v-btn>
							<v-btn color="blue darken-1" flat @click="dialog = false" v-on:click="registMember">Regist</v-btn>
						</v-card-actions>
					</v-card>
				</v-dialog>
			</v-layout>
		</v-flex>
      </v-layout>
    </v-flex>
  </v-layout>
</template>
<script>
import FirebaseService from '@/services/FirebaseService'
import store from '../store.js'
import firebase, { database } from 'firebase/app'
export default {
	name: 'LoginPage',
	data() {
		return {
			drawer: null,
			right: null,
			dialog: false,
			name: '',
			email: '',
			album: '',
			password: '',
			age: '',
			auth: '',
			myauth: ''
		}
	},
	methods: {
		async loginWithGoogle() {
			const self = this;
			const result = await FirebaseService.loginWithGoogle()
			this.$store.state.accessToken = result.credential.accessToken
			this.$store.state.user = result.user
			self.$router.push('/')
		},
		async loginWithFacebook() {
			const self = this;
			const result = await FirebaseService.loginWithFacebook()
			this.$store.state.accessToken = result.credential.accessToken
			this.$store.state.user = result.user
			self.$router.push('/')
		},
		loginWithEmail(e) {
			const result = FirebaseService.loginService(e, this.email, this.password)
		},
		close() {
			this.name= ''
			this.email= ''
			this.album= ''
			this.password= ''
			this.age= ''
			this.auth = ''
      	},
		registMember() {
			if(this.auth == this.myauth) {
				alert('회원가입이 완료 되었습니다!');
				let msg = FirebaseService.postMember(this.name, this.password, this.email, this.album, this.age);
			} else {
				alert('인증 번호가 일치하지 않습니다, 확인 후 재입력 부탁드립니다!')
			}
			this.close()
		},
		sendEmail() {
			this.$axios({
				method: 'GET',
				url: "http://localhost:3000?address=" + this.email,
				headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
				}).then(response => {
					console.log(this.email)
					this.myauth = response.config.data;
					console.log(response)
				}).catch(function(error) {
					console.log(error);
				});
		}
	}
};
</script>
