<template>
    <div id="outer-container">
        <div class="left">

        </div>
        <div class="right">
            <h2 class="title-text">Create a Chat App account</h2>
            <form @submit.prevent="register" id="register-box">
                <div class="form-input-group">
                    <label for="username">Username</label>
                    <input type="text" id="username" v-model="user.username" required autofocus />
                </div>
                <div class="form-input-group">
                    <label for="username">Email</label>
                    <input type="text" id="email" v-model="user.email" required autofocus />
                </div>
                <div class="form-input-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" v-model="user.password" required />
                </div>
                <div class="form-input-group">
                    <label for="confirmPassword">Confirm Password</label>
                    <input type="password" id="confirmPassword" v-model="user.confirmPassword" required />
                </div>
                <button type="submit">Create Account</button>
                <p><router-link :to="{ name: 'login' }">Already have an account? Log in.</router-link></p>
            </form>
        </div>

    </div>
    <button type="button" v-on:click="debuging">Debug</button>
</template>

<script>

export default {
    name: 'RegisterPage',

    data() {
        return {
            user: {
                username: '',
                password: '',
                confirmPassword: '',
                email: '',
                role: 'ROLE_USER',
            }
        }
    },
    methods: {
        debuging() {
            window.alert(
                ' user name: ' + this.user.username
                + '\n email: ' + this.user.email
                + '\n  password: ' + this.user.password
                + '\n  confirm : ' + this.user.confirmPassword
            )
        },

    },
}
</script>

<style scoped>
@import url(https://fonts.googleapis.com/css?family=Roboto:400,300,500);

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
    width: 700px;
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
    width: 300px;
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
    width: 220px;
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