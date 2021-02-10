<template>
    <div class="monitor">
        <iframe v-if="isShow" :style="nodeStyle" src="http://172.16.24.5:9001/graph"></iframe>
    </div>
</template>


<script>
    export default {
        data() {
            return {
                nodeStyle: {},
                isShow: false
            }
        },
        mounted() {
            const vnode = document.querySelector('.ant-layout-content')
            console.log('----', vnode.offsetHeight)
            this.nodeStyle.width = vnode.clientWidth + 'px'
            this.nodeStyle.height = vnode.clientHeight + 'px'
            this.isShow = true

        }
    }

</script>

<style>
    .monitor {
        width: 100%;
        height: 100%;
        overflow: auto;
    }
</style>
