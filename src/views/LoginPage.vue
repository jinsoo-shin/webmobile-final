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
<div>
<Header></Header>
  <v-layout align-center justify-center row fill-height class="mt-2">
    <v-flex sm8 lg4 text-xs-center v-if="width>=600">
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
						<v-btn xs12 sm4 md4 color="#8C7875" block dark type='submit' class="mt-4" @click="loginWithEmail" style="">로그인</v-btn>
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
						<v-card-title center>
							<span style="margin: 0 auto; font-family: 'Nanum Gothic Coding', monospace; font-size:xx-large; font-weight: bold;">SIGN UP</span>
						</v-card-title>
						<v-card-text>
							<div style="text-align: right; font-size:small;">
								*는 필수 입력값입니다.
							</div>
							<v-container grid-list-md>
								<v-layout wrap>
									<v-flex xs12 sm6 md4>
										<v-text-field label="닉네임*" v-model="name" style="ime-mode:disabled;" :rules="[v => !!v || '닉네임을 입력해주세요.']" required></v-text-field>
									</v-flex>
									<v-flex xs9>
										<v-text-field label="이메일*" v-model="email" :rules="emailRules" required></v-text-field>
									</v-flex>
									<v-flex xs3>
										<v-btn v-on:click="sendEmail" outline>인증번호발송</v-btn>
									</v-flex>
									<v-flex xs12>
										<v-text-field label="인증번호*" v-model="auth" :rules="[v => !!v || '인증번호를 입력해주세요.']" required></v-text-field>
									</v-flex>
									<v-flex xs12>
										<v-text-field label="Imgur앨범주소*" v-model="album" :rules="[v => !!v || 'Imgur앨범주소를 입력해주세요.']" required></v-text-field>
									</v-flex>
									<v-flex xs12>
										<v-text-field label="비밀번호*" type="password" v-model="password" :rules="[v => !!v || '비밀번호를 입력해주세요.']" required></v-text-field>
									</v-flex>
									<v-flex xs12 sm6>
										<v-text-field label="나이*" v-model="age" :rules="[v => !!v || '나이를 입력해주세요.']" required></v-text-field>
									</v-flex>
								</v-layout>
							</v-container>
						</v-card-text>
						<v-divider class="mt-12"></v-divider>
						<v-card-actions>
							<v-spacer></v-spacer>
							<v-btn outline flat @click="dialog = false" v-on:click="close">닫기</v-btn>
							<v-btn outline flat @click="dialog = false" v-on:click="registMember">가입</v-btn>
						</v-card-actions>
					</v-card>
				</v-dialog>
			</v-layout>
		</v-flex>
      </v-layout>
    </v-flex>
	<v-flex v-if="width<600" xs10 sm8 lg4 text-xs-center>
      <v-layout align-center justify-center row fill-height elevation-5 style="min-height:500px;" white pa-5>
        <v-flex xs12 text-xs-center>
			<h1 class="mt-4" style="font-family: 'Nanum Gothic Coding', monospace;">LOGIN</h1>
			<template>
				<form v-on:submit='loginWithEmail'>
					<v-text-field type='email' v-model="email" label="E-mail" required></v-text-field>
					<v-text-field type='password' v-model="password" label="Password" required></v-text-field>
					<v-btn color="#8C7875" dark type='submit' style="width:100%;">로그인</v-btn>
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
							<span style="margin: 0 auto; font-family: 'Nanum Gothic Coding', monospace; font-size:xx-large; font-weight: bold;">SIGN UP</span>
						</v-card-title>
						<v-card-text>
							<div style="text-align: right; font-size:small;">
								*는 필수 입력값입니다.
							</div>
							<v-container grid-list-md>
								<v-layout wrap>
									<v-flex xs12 sm6 md4>
										<v-text-field label="이름*" v-model="name" required></v-text-field>
									</v-flex>
									<v-flex xs10>
										<v-text-field label="이메일*" v-model="email" required></v-text-field>
									</v-flex>
									<v-flex xs2>
										<v-btn text icon v-on:click="sendEmail"><v-icon color="indigo">send</v-icon></v-btn>
									</v-flex>
									<v-flex xs12>
										<v-text-field label="인증번호*" v-model="auth" required></v-text-field>
									</v-flex>
									<v-flex xs12>
										<v-text-field label="Imgur앨범주소*" v-model="album" required></v-text-field>
									</v-flex>
									<v-flex xs12>
										<v-text-field label="비밀번호*" type="password" v-model="password" required></v-text-field>
									</v-flex>
									<v-flex xs12 sm6>
										<v-text-field label="나이*" v-model="age" required></v-text-field>
									</v-flex>
								</v-layout>
							</v-container>
						</v-card-text>
						<v-divider class="mt-12"></v-divider>
						<v-card-actions>
							<div style="margin:0 auto;">
								<v-btn color="indigo" flat outline @click="dialog = false" v-on:click="close">닫기</v-btn>
								<v-btn color="indigo" flat outline @click="dialog = false" v-on:click="registMember">가입</v-btn>
							</div>
						</v-card-actions>
					</v-card>
				</v-dialog>
			</v-layout>
		</v-flex>
      </v-layout>
    </v-flex>
  </v-layout>
  <Footer></Footer>
</div>
</template>
<script>
import FirebaseService from '@/services/FirebaseService'
import LoginService from '@/services/LoginService'
import store from '../store.js'
import firebase, { database } from 'firebase/app'
import Header from '../components/Header'
import Footer from '../components/Footer'

export default {
	name: 'LoginPage',
	components :{
		Header,
		Footer
	},
	data() {
		return {
			drawer: null,
			right: null,
			dialog: false,
			name: '',
			email: '',
			emailRules: [
				v => !!v || 'E-mail을 필수로 입력해주시기 바랍니다.',
				v => /.+@.+\..+/.test(v) || '올바른 E-mail 형식이 아닙니다.',
			],
			album: '',
			password: '',
			age: '',
			auth: '',
			myauth: '',
			width:0
		}
	},
	created(){
	window.addEventListener('resize', this.handleResize)
    this.handleResize();
	},
	destroyed() {
    window.removeEventListener('resize', this.handleResize)
  	},
	methods: {
		handleResize() {	
			this.width = window.innerWidth;
		},
		async loginWithGoogle() {
			const self = this;
			const result = await LoginService.loginWithGoogle()
			this.$store.state.accessToken = result.credential.accessToken
			this.$store.state.user = result.user
			self.$router.push('/')
		},
		async loginWithFacebook() {
			const self = this;
			const result = await LoginService.loginWithFacebook()
			this.$store.state.accessToken = result.credential.accessToken
			this.$store.state.user = result.user
			self.$router.push('/')
		},
		loginWithEmail(e) {
			const result = LoginService.loginService(e, this.email, this.password)
			LoginService.getMember(this.email);
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
				let msg = LoginService.postMember(this.name, this.password, this.email, this.album, this.age);
			this.close()
			} else {
				alert(this.auth +" " + this.myauth +'인증 번호가 일치하지 않습니다. 확인 후 재입력 부탁드립니다!')
			}
		},
		sendEmail() {
			this.$axios({
				method: 'GET',
				url: "http://52.78.157.214:3000?address=" + this.email,
				headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
				}).then(response => {
					alert("이메일로 인증번호가 발송되었습니다.")
					this.myauth = response.data;
				}).catch(function(error) {
				});
		}
	}
};
</script>
