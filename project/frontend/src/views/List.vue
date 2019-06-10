<template>
    <div>
        <Table stripe :columns="columns1" :data="data1" style="margin: 20px;"></Table>

    </div>
</template>
<script>
    import axios from 'axios'

    export default {
        name: "List",
        props: ["data1"],
        data() {
            return {
                columns1: [
                    {
                        title: '活动名称',
                        key: 'name'
                    },
                    {
                        title: '素拓项目',
                        key: 'Qnum',
                        filters: [
                            {
                                label: '1151',
                                value: 1
                            },
                            {
                                label: '1152',
                                value: 2
                            },
                            {
                                label: '1081',
                                value: 3
                            },
                            {
                                label: '1143',
                                value: 4
                            },

                        ],
                        filterMultiple: false,
                        filterMethod (value, row) {
                            if (value === 1) {
                                return row.Qnum === '1151';
                            } else if (value === 2) {
                                return row.Qnum === '1152';
                            } else if (value === 3) {
                                return row.Qnum === '1081';
                            } else if (value === 4) {
                                return row.Qnum === '1143';
                            }
                        }
                    },
                    {
                        title: '素拓分数',
                        key: 'Score',
                        sortable: true
                    },
                    {
                        title: '活动地址',
                        key: 'location'
                    },
                    {
                        title: '报名开始',
                        key: 'sign_start_time'
                    },
                    {
                        title: '报名结束',
                        key: 'sign_end_time'
                    },
                    {
                        title: '活动时间',
                        key: 'start_time'
                    }
                ],
            }
        }
    }
</script>
