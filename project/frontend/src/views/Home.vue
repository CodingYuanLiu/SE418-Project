<template>
    <div class="home">
        <Row style="margin-top: 20px; " :gutter="16">
            <Col span="12">
                <ItemDisplay entry="1151" :data1="data1"></ItemDisplay>
            </Col>
            <Col span="12">
                <ItemDisplay entry="1152" :data1="data1"></ItemDisplay>
            </Col>
        </Row>
        <Row style="margin-top: 20px; " :gutter="16">
            <Col span="12">
                <ItemDisplay entry="1143" :data1="data1"></ItemDisplay>
            </Col>
            <Col span="12">
                <ItemDisplay entry="1081" :data1="data1"></ItemDisplay>
            </Col>
        </Row>
    </div>
</template>

<script>
    // @ is an alias to /src
    // import HelloWorld from '@/components/HelloWorld.vue'

    // import Col from "iview/src";
    import ItemDisplay from "@/components/ItemDisplay";

    export default {
        name: 'home',
        props: ["data1"],
        components: {
            ItemDisplay
            // Col
            // HelloWorld
        }
    }
</script>
