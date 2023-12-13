<template>
    <div id="register-box" class="text-center">
        <img src="./images/three-robots.jpg" class="register-pic" />
        <div class="left">
            <h1 class="title-text">Create Your Account</h1>
        </div>
        <div class="right">
            <form @submit.prevent="register">
                <div role="alert" v-if="registrationErrors">
                    {{ registrationErrorMsg }}
                </div>
                <div class="form-input-group">
                    <label for="username">Username</label>
                    <input type="text" id="username" v-model="user.username" required autofocus />
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
</template>

<script>

export default {
    name: 'register',
    data() {
        return {
            user: {
                username: '',
                password: '',
                confirmPassword: '',
                role: 'user',
            }
        }
    }
}
</script>

<style scoped>
.register-pic {
    width: 600px;
}

@import url(https://fonts.googleapis.com/css?family=Roboto:400,300,500);

*:focus {
    outline: none;
}

body {
    margin: 0;
    padding: 0;
    background: #DDD;
    font-size: 16px;
    color: #222;
    font-family: 'Roboto', sans-serif;
    font-weight: 300;
}

#register-box {
    position: relative;
    margin: 5% auto;
    width: 600px;
    height: 310px;
    background: #FFF;
    border-radius: 2px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.4);
}

.right {
    position: absolute;
    top: 0;
    right: 0;
    box-sizing: border-box;
    padding: 40px;
    width: 300px;
    height: 400px;
}

h1 {
    margin: 0 0 20px 0;
    font-weight: 300;
    font-size: 28px;
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
    color: #16a085;
    transition: 0.2s ease;
}

input[type="submit"] {
    margin-top: 28px;
    width: 120px;
    height: 32px;
    background: #16a085;
    border: none;
    border-radius: 2px;
    color: #FFF;
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

.or {
    position: absolute;
    top: 180px;
    left: 280px;
    width: 40px;
    height: 40px;
    background: #DDD;
    border-radius: 50%;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.4);
    line-height: 40px;
    text-align: center;
}

.left {
    position: absolute;
    top: 0;
    left: 0;
    box-sizing: border-box;
    padding: 40px;
    width: 300px;
    height: 400px;
    background: url('https://goo.gl/YbktSj');
    background-size: cover;
    background-position: center;
    border-radius: 0 2px 2px 0;
}

.left .loginwith {
    display: block;
    margin-bottom: 40px;
    font-size: 28px;
    color: #FFF;
    text-align: center;
}

button.social-signin {
    margin-bottom: 20px;
    width: 220px;
    height: 36px;
    border: none;
    border-radius: 2px;
    color: #FFF;
    font-family: 'Roboto', sans-serif;
    font-weight: 500;
    transition: 0.2s ease;
    cursor: pointer;
}

button.social-signin:hover,
button.social-signin:focus {
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.4);
    transition: 0.2s ease;
}

button.social-signin:active {
    box-shadow: 0 1px 2px rgba(0, 0, 0, 0.4);
    transition: 0.2s ease;
}

button.social-signin.facebook {
    background: #32508E;
}

button.social-signin.twitter {
    background: #55ACEE;
}

button.social-signin.google {
    background: #DD4B39;
}
</style>