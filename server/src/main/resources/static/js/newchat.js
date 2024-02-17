new Vue({
    el: '#app',
    data() {
        return {
            modalVisible: false,
            chatInput: '',
            textToType: "",
            typedText: "",
            index: 0,
        }
    },
    methods: {

        showModal() {
            this.modalVisible = true;
            this.startTextToType("您输入的prompt将作为Ai的系统默认角色,请务必好好填写,不然会影响对话体验！输入为空则进入系统默认");

        },
        submitData() {
            // 关闭模态框
            this.modalVisible = false;
            this.chatInput = '';
        },
        cancel() {
            this.modalVisible = false;
            this.chatInput = '';
            this.stopTextToType();
        },

        startTextToType(text) {
            this.textToType = text;
            this.typedText = "";
            this.index = 0;
            this.typeText();
        },
        stopTextToType(){
            this.textToType = "";
            this.typedText = "";
            this.index = 0;
        },
        typeText() {
            if (this.index < this.textToType.length) {
                this.typedText += this.textToType[this.index];
                this.index++;
                setTimeout(this.typeText, 100); // 调整打字速度
            }
        },
    }
});


