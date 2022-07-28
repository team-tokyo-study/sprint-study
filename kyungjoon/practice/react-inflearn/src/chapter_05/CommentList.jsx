import React from "react";
import Comment from "./Comment";

const comments = [
    {
        name: "KyungJoon Kim",
        comment: "첫 댓글입니다",
    },
    {
        name: "김경준",
        comment: "리액트 실습중",
    },
    {
        name: "KyungJoon Kim",
        comment: "아직은 어려운 듯",
    },
];


function CommentList(props) {
    return (
        <div>
            {comments.map((comment) => {
                return (
                    <Comment name={comment.name} comment={comment.comment} />
                );
            })}
        </div>
    );
}

export default CommentList;