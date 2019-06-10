<style scoped>
    .layout-con {
        height: 100%;
        width: 100%;
    }

    .menu-item span {
        display: inline-block;
        overflow: hidden;
        width: 69px;
        text-overflow: ellipsis;
        white-space: nowrap;
        vertical-align: bottom;
        transition: width .2s ease .2s;
    }

    .menu-item i {
        transform: translateX(0px);
        transition: font-size .2s ease, transform .2s ease;
        vertical-align: middle;
        font-size: 16px;
    }

    .collapsed-menu span {
        width: 0px;
        transition: width .2s ease;
    }

    .collapsed-menu i {
        transform: translateX(5px);
        transition: font-size .2s ease .2s, transform .2s ease .2s;
        vertical-align: middle;
        font-size: 22px;
    }
</style>
<template>
    <div class="layout">
        <Layout :style="{minHeight: '100vh'}">
            <Sider collapsible :collapsed-width="78" v-model="isCollapsed">
                <Menu active-name="1-1" theme="dark" width="auto" :class="menuitemClasses">
                    <router-link to="/home">
                        <MenuItem name="1-1">
                            <Icon type="ios-home-outline"/>
                            <span>主页</span>
                        </MenuItem>
                    </router-link>
                    <router-link to="/list">
                        <MenuItem name="1-2">
                            <Icon type="ios-list"/>
                            <span>素拓列表</span>
                        </MenuItem>
                    </router-link>
                    <router-link to="/about">
                        <MenuItem name="1-3">
                            <Icon type="ios-information-circle-outline"/>
                            <span>关于</span>
                        </MenuItem>
                    </router-link>
                </Menu>
                <Button style="margin-left: 10px" @click="login">登录</Button>
            </Sider>
            <Layout>
                <Header :style="{background: '#fff', boxShadow: '0 2px 3px 2px rgba(0,0,0,.1)'}">
                    <label style="font-size: 26px">素拓活动推荐系统</label>
                </Header>
                <Content :style="{padding: '0 16px 16px'}">
                    <!--          <Breadcrumb :style="{margin: '16px 0'}">-->
                    <!--            <BreadcrumbItem>Home</BreadcrumbItem>-->
                    <!--            <BreadcrumbItem>Components</BreadcrumbItem>-->
                    <!--            <BreadcrumbItem>Layout</BreadcrumbItem>-->
                    <!--          </Breadcrumb>-->
                    <!--          <Card>-->
                    <!--            <div style="height: 600px">Content</div>-->
                    <!--          </Card>-->
                    <router-view :data1="data1"></router-view>
                </Content>
            </Layout>
        </Layout>
        <BackTop></BackTop>
    </div>
</template>
<script>
    import axios from 'axios'
    import qs from 'qs'

    export default {
        data() {
            return {
                isCollapsed: true,
                token: "",
                data1: [],
                error: {}
            };
        },
        computed: {
            menuitemClasses: function () {
                return [
                    'menu-item',
                    this.isCollapsed ? 'collapsed-menu' : ''
                ]
            }
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
                    // console.log(response);
                    this.token = response.data.access_token;
                    // console.log("as");
                    axios.get("http://47.102.101.146:10000/tongqu-parser/act/parse?access_token="+this.token)
                        .then((response) => {
                            // console.log(response);
                            this.data1 = JSON.parse(response.data);
                        });
                }).catch((error) => {
                    // console.log(error);
                    this.error = error;
                });
            }
        }
    }
</script>
