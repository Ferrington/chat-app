<template>
    <registration-nav />
    <main class="form-signin w-100 m-auto">
        <form>
            <h1 class="h3 mb-3 fw-normal">Please sign in to test RegistrationPage.vue</h1>
            <input type="text" class="form-control" placeholder="User Name" required>
            <input type="password" class="form-control" placeholder="Password" required>
            <button class="btn btn-primary w-100 py-2" type="submit">Sign in</button>
        </form>
    </main>
</template>

<script>
import RegistrationNav from '@/components/Registration/RegistrationNav.vue'
export default {
    name: "TestLogin",
    components: {
        RegistrationNav,
    },
}
</script>

<style scoped>
html,
body {
    height: 100%;
}

.form-signin {
    max-width: 330px;
    padding: 1rem;
}

.form-signin .form-floating:focus-within {
    z-index: 2;
}

.form-signin input[type="email"] {
    margin-bottom: -1px;
    border-bottom-right-radius: 0;
    border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
    margin-bottom: 10px;
    border-top-left-radius: 0;
    border-top-right-radius: 0;
}
</style>
