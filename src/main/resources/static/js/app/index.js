var main = {
    init : function() {
        const btnSave = document.getElementById('btn-save');
        if (btnSave) {
            btnSave.addEventListener('click', () => this.save());
        }

        const btnUpdate = document.getElementById('btn-update');
        if (btnUpdate) {
            btnUpdate.addEventListener('click', () => this.update());
        }

        const btnDelete = document.getElementById('btn-delete');
        if (btnDelete) {
            btnDelete.addEventListener('click', () => this.delete());
        }
    },
    save : function() {
        var data = {
            title: document.getElementById('title').value,
            author: document.getElementById('author').value,
            content: document.getElementById('content').value
        };

        fetch('/api/v1/posts', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        })
        .then(res => {
            if (!res.ok) { throw new Error('Request failed'); }
            return res.json();
        })
        .then(() => {
            alert('글이 등록되었습니다.');
            window.location.href = "/";
        })
        .catch(err => console.log(err));
    },
    update : function() {

        var data = {
            title: document.getElementById('title').value,
            content: document.getElementById('content').value
        };

        var id = document.getElementById('id').value;

        fetch(`/api/v1/posts/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        })
        .then(res => {
            if (!res.ok) { throw new Error('Request failed'); }
        })
        .then(() => {
            alert('글이 수정되었습니다.');
            window.location.href = "/";
        })
        .catch(err => console.log(err));
    },
    delete : function() {

        var id = document.getElementById('id').value;

        fetch(`/api/v1/posts/${id}`, {
            method: 'DELETE',
            headers: { 'Content-Type': 'application/json' },
        })
        .then(res => {
            if (!res.ok) { throw new Error('Request failed'); }
        })
        .then(() => {
            alert('글이 삭제되었습니다.');
            window.location.href = "/";
        })
        .catch(err => console.log(err));
    },
};

document.addEventListener('DOMContentLoaded', () => {
    main.init();
});
