new Vue({
    el: '#app',
    data: {
        isDropdownVisible: false,
        options: [
            { value: 'option1', text: 'Default(GPT-3.5)' },
            { value: 'option2', text: 'Legacy(GPT-3.5)' },
            { value: 'option3', text: 'GPT-4' },
        ],
        selectedOption: null,
    },
    mounted() {
        // 当点击下拉框以外的区域时，关闭下拉框
        this.outsideClickListener = (event) => {
            if (!$(event.target).closest('.dropdown').length) {
                this.isDropdownVisible = false;
            }
        };
        $(document).on('click', this.outsideClickListener);
    },
    beforeDestroy() {
        // 移除全局监听器
        $(document).off('click', this.outsideClickListener);
    },
    methods: {
        toggleDropdown() {
            this.isDropdownVisible = !this.isDropdownVisible;
        },
        selectOption(option) {
            this.selectedOption = option;
            this.isDropdownVisible = false;
        },
    },
});
