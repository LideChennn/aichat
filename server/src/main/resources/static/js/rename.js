const app = new Vue({
    el: '#app',
    data: {
        currentFilename: 'example.txt',
        newFilename: ''
    },
    methods: {
        renameFile() {
            if (this.newFilename.trim()) {
                this.currentFilename = this.newFilename.trim();
                this.newFilename = '';
                alert('文件重命名成功！');
            } else {
                alert('请输入有效的新文件名。');
            }
        }
    }
});