<template>
  <div>
      <AdminHeader></AdminHeader>
      <Admin></Admin>
  </div>
</template>

<script>
import ImgBanner from '../components/ImgBanner'
import Admin from '../components/Admin'
import AdminHeader from '../components/AdminHeader'

export default {
	name: 'AdminPage',
	components: {
        ImgBanner,
        Admin,
        AdminHeader
	}
}
</script>
