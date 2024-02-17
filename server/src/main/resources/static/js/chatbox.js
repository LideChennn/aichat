new Vue({
    el: '#app',
    data: {
        messages: [
            {
                text: 'Hello!',
                isMine: false
            }, {
                text: 'Hi!',
                isMine: true
            },{
                text: 'Hello!',
                isMine: false
            }, {
                text: 'Hi!',
                isMine: true
            },{
                text: 'Hello!',
                isMine: false
            }, {
                text: 'Hi!',
                isMine: true
            },{
                text: 'Hello!',
                isMine: false
            }, {
                text: 'Hi!',
                isMine: true
            },
        ],
        inputText: ''
    },
    methods: {
        sendMessage() {
            if (this.inputText.trim()) {
                this.messages.push({text: this.inputText.trim(), isMine: true});
                this.inputText = '';              this.$nextTick(() => {
                    this.scrollToBottom();
                });
            }
        },
        /**
         * 我们将messages div的引用设置为messages，
         * 然后在scrollToBottom方法中使用这个引用来设置滚动条的位置。
         * 当sendMessage方法被调用时，我们使用$nextTick确保在DOM更新后
         * 再调用scrollToBottom方法.
         */
        scrollToBottom() {
            this.$refs.messageContainer.scrollTop = this.$refs.messageContainer.scrollHeight;
        }
    }
});
