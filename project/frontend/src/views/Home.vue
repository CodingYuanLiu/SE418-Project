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
        <Row>
        </Row>
    </div>
</template>

<script>
    // @ is an alias to /src
    // import HelloWorld from '@/components/HelloWorld.vue'

    // import Col from "iview/src";
    import ItemDisplay from "@/components/ItemDisplay";
    import axios from 'axios'
    import qs from 'qs'

    export default {
        name: 'home',
        props: ["data1"],
        components: {
            ItemDisplay
            // Col
            // HelloWorld
        },
        methods: {
            login() {
                axios.post("http://47.102.101.146:10000/auth-server/oauth/token", qs.stringify({
                    username: "summer855",
                    password: "123456",
                    clientId: "summer855",
                    clientSecret: "123456",
                    scope: "all",
                    grant_type: "password"
                }), {
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded',
                        'Authorization': "Basic c3VtbWVyODU1OjEyMzQ1Ng=="
                    }
                }).then((response) => {
                    console.log(response);
                    this.token = response.data.access_token;
                }).catch((error) => {
                    console.log(error);
                });
            }
        }
    }
</script>
