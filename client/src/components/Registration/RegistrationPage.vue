<template>
    <div id="outer-container">
        <div class="left">

        </div>
        <div class="right">
            <h2 class="title-text">Create a Chat App account</h2>

            <form @submit.prevent="register" id="register-box">
                <div class="form-input-group">
                    <label for="username">Username</label>
                    <div v-if="usernameOk === false" style="color: orange;">
                        Length >= 2
                    </div>
                    <input type="text" id="username" v-model="username" @input="validateUsername" required autofocus />
                </div>
                <div class="form-input-group">
                    <label for="email">Email</label>
                    <div v-if="emailOk === false" style="color: orange;">
                        Valid email address
                    </div>
                    <input type="text" id="email" v-model="email" @input="validateEmail" required autofocus />
                </div>
                <div class="form-input-group">
                    <label for="password">Password</label>
                    <div v-if="passwordOk === false" style="color: orange;">
                        length >= 8, Upper, lower, special character and digit
                    </div>
                    <div class="password-input-container">
                        <input id="password" v-model="password" @input="validatePassword" required ref="passwordInput"
                            :type="showPassword ? 'text' : 'password'" />
                        <button class="round-button" @click="togglePassword">{{ showPassword ? 'Hide' : 'Show' }} </button>
                    </div>
                </div>
                <div class="form-input-group">
                    <label for="confirmPassword">Confirm Password</label>
                    <div class="password-input-container">
                        <input id="confirmPassword" v-model="confirmPassword" @input="validatePassword" required
                            ref="passwordConfInput" :type="showConfPassword ? 'text' : 'password'" />
                        <button class="round-button" @click="toggleConfPassword">{{ showConfPassword ? 'Hide' : 'Show' }}
                        </button>
                    </div>
                </div>
                <p v-if="passwordsMatch === false && confirmPassword.length > 0" style="color: orange;">
                    Passwords must match
                </p>


                <button type="submit"
                    :disabled="passwordsMatch === false || passwordOk == false || usernameOk === false || emailOk === false">
                    Create Account
                </button>
                <!-- <p><router-link :to="{ name: 'login' }">Already have an account? Log in.</router-link></p> -->
            </form>
        </div>

    </div>
</template>

<script lang="ts">
import router from '@/router';
import authService from '@/services/AuthService';
import { reactive } from 'vue';
import { ref } from 'vue';

export default {
    name: 'RegisterPage',
    comportents: {
        authService,
    },
    setup() {
        const username = ref('');
        const email = ref('')
        const password = ref('');
        const confirmPassword = ref('');

        const usernameOk = ref(false);
        const emailOk = ref(false);
        const passwordOk = ref(false)
        const passwordsMatch = ref(false);
        const showPassword = ref(false);
        const showConfPassword = ref(false);

        const validateUsername = () => {
            usernameOk.value = username.value.length >= 2
        }
        const validateEmail = () => {
            emailOk.value = email.value.includes('@') && email.value.includes('.')
                && email.value.indexOf('@') < email.value.indexOf('.');
        };
        const validatePassword = () => {
            passwordsMatch.value = password.value === confirmPassword.value;
            validateStrongPassword();
        };

        const data = reactive(
            {
                "username": "",
                "email": "",
                "role": ["user"],
                "password": '',
            }
        );

        const register = async () => {

            if (passwordsMatch.value) {
                data["username"] = username.value
                data["email"] = email.value;
                data["password"] = password.value;

                await authService.register(
                    data
                );

            } else {
                window.alert("password and confirmed password don't match. ")
            }
        };
        const minLength = 8;
        const validateStrongPassword = () => {
            passwordOk.value =
                password.value.length >= minLength
                &&
                /[A-Z]/.test(password.value) === true
                &&
                /[a-z]/.test(password.value) === true
                &&
                /[1234567890]/.test(password.value) === true
                &&
                /[!@#$%^&*]/.test(password.value) === true;
            console.log('validateStrongPassword called: ' + passwordOk.value);
        };

        const togglePassword = () => {
            // Toggle the showPassword flag
            showPassword.value = !showPassword.value;

            // Change the input type based on the showPassword flag
            const passwordInput = this.$refs.passwordInput;
            passwordInput.type = showPassword.value ? 'text' : 'password';
        };
        const toggleConfPassword = () => {
            // Toggle the showPassword flag
            showConfPassword.value = !showConfPassword.value;

            // Change the input type based on the showPassword flag
            const passwordConfInput = this.$refs.passwordConfInput;
            passwordConfInput.type = showConfPassword.value ? 'text' : 'password';
        };

        return {
            data,
            username,
            email,
            password,
            confirmPassword,
            passwordsMatch,
            validatePassword,
            validateUsername,
            validateEmail,
            usernameOk,
            emailOk,
            register,
            passwordOk,
            validateStrongPassword,
            showPassword,
            showConfPassword,
            togglePassword,
            toggleConfPassword,
        }
    },
    computed: {
    },
}
</script>

<style scoped>
/* @import url(https://fonts.googleapis.com/css?family=Roboto:400,300,500); */

*:focus {
    outline: none;
}

body {
    margin: 0;
    padding: 0;
    box-sizing: border-box;

    background: #DDD;
    font-size: 1rem;
    color: #222;
    font-family: 'Roboto', sans-serif;
    font-weight: 300;
}

#outer-container {
    /* background-color: rebeccapurple; */
    display: flex;
    justify-content: space-between;
    width: 800px;
    height: 500px;

    margin: 10px auto;


    border-radius: 2px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.4);
}

.left {
    /* background-color: #DD4B39; */
    background-image: url("welcome-buffet.jpg");
    background-size: cover;
    flex-shrink: 2;
    width: 50%;


    /* background-color: #DD4B39; */
}

.right {
    /* background-color: chartreuse; */
    padding: 40px;
    width: 500px;
    height: 400px;
}

.title-text {
    display: block;
    margin-top: 1px;
    font-size: 1.5rem;
    font-weight: 600;
    letter-spacing: -.2px;
    text-align: center;
    line-height: 1.5rem;
}

#register-box {
    margin: auto;
    /* background-color: chocolate; */
    display: flex;
    flex-direction: column;
}

input[type="text"],
input[type="password"] {
    display: block;
    box-sizing: border-box;
    margin-bottom: 20px;
    padding: 4px;
    width: 400px;
    height: 32px;
    border: none;
    border-bottom: 1px solid #AAA;
    font-family: 'Roboto', sans-serif;
    font-weight: 400;
    font-size: 15px;
    transition: 0.2s ease;
}

input[type="text"]:focus,
input[type="password"]:focus {
    border-bottom: 2px solid #16a085;

    /* color: #16a085; */
    transition: 0.2s ease;
}

input[type="submit"] {
    margin-top: 28px;
    width: 120px;
    height: 32px;
    background: #16a085;
    border: none;
    border-radius: 2px;
    /* color: #FFF; */
    font-family: 'Roboto', sans-serif;
    font-weight: 500;
    text-transform: uppercase;
    transition: 0.1s ease;
    cursor: pointer;
}

input[type="submit"]:hover,
input[type="submit"]:focus {
    opacity: 0.8;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.4);
    transition: 0.1s ease;
}

input[type="submit"]:active {
    opacity: 1;
    box-shadow: 0 1px 2px rgba(0, 0, 0, 0.4);
    transition: 0.1s ease;
}

.password-input-container {
    display: flex;
}

.round-button {
    font-size: 0.7rem;
    width: 30px;
    height: 30px;
    padding: 1px 1px;
    /* Adjust padding for size */
    border-radius: 20px;
    /* Adjust border-radius for roundness */
    background-color: #007bff;
    /* Button background color */
    color: #fff;
    /* Button text color */
    border: none;
    /* Remove button border */
    cursor: pointer;
}

/* Style when button is hovered */
.round-button:hover {
    background-color: #0056b3;
    /* Adjust hover background color */
}

/* .or {

    top: 180px;
    left: 280px;
    width: 40px;
    height: 40px;
    background: #DDD;
    border-radius: 50%;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.4);
    line-height: 40px;
    text-align: center;
} */
</style>