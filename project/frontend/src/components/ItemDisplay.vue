<template>
    <div>
        <Card dis-hover>
            <p slot="title">最新{{entry}}项素拓</p>
            <router-link to="/list" slot="extra">
                更多
            </router-link>
            <ul style=" font-size: 14px; padding-left: 10px;">
                <li v-for="item in dataShow" style="margin: 5px">
                    <a target="_blank" style="color: black" :href="'http://tongqu.me/act/'+item.actid">[{{item.Score}}分]{{item.name}}</a>
                </li>
            </ul>
        </Card>
    </div>
</template>

<script>
    export default {
        name: "ItemDisplay",
        props: ["entry", "data1"],
        data() {
            return {
                dataShow: [],
            }
        },
        created: function () {
            console.log(this.data1);
            let i = 0;
            let j = 0;
            let length = this.data1.length;
            while (i < 5 && j < length) {
                if (this.data1[j].Qnum === this.entry) {
                    this.dataShow[i] = this.data1[j];
                    i++;
                }
                j++;
            }
        }
    }
</script>

<style scoped>

</style>