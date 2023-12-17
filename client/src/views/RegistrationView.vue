
<template>
    <h1>Registration View</h1>
    <registration-nav />
    <registration-page />
</template>
<script >

import RegistrationPage from '@/components/Registration/RegistrationPage.vue'
import RegistrationNav from '@/components/Registration/RegistrationNav.vue'

export default {
    components: {
        RegistrationPage,
        RegistrationNav,
    },
}    
</script>


<style scoped></style>