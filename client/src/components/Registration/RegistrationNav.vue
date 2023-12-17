<template>
    <nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">
        <div class="container-fluid">
            <div>
                <ul class="navbar-nav me-auto mb-2 mb-md-0">
                    <li class="nav-item">
                        <router-link :to="{ name: 'test-login' }" class="nav-link active">
                            Login
                        </router-link>
                    </li>
                    <li class="nav-item">
                        <router-link :to="{ name: 'register' }" class="nav-link active">
                            Register
                        </router-link>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</template>

<script lang="ts">
export default {
    name: "RegistrationNav"
}
</script>

<style scoped></style>