.text
.global _decode, _encode

/** The _decode subroutine receives (via the C calling convention):
    **     arg1: address of a buffer containing a rlc-encoded message
    **     arg2: address of a buffer to write the output
    ** and it should:
    **     1. decode the rlc message and write the result to the output buffer
    **     2. return the number of bytes that were written to the buffer
    ** also, whenever your subroutine returns, it must have preserved:
    **     1. the stack pointer (no extra pushes or pops)
    **     2. the frame pointer (x29)
    **     3. all non-volatile registers (x19 ~ x28)
    **     4. the contents of the input buffer
**/
_decode:
    /* Set up stack */
    stp x29, x30, [sp, #-16]!
    mov x29, sp

    ldr x2, [sp, #16]             /* Input pointer */
    ldr x3, [sp, #24]             /* Output pointer */
    mov x4, #0                    /* Total bytes read */

/* Main loop responsible for decoding. */
decode_loop:
    ldrb w5, [x2]                 /* Load the first character */
    cbz w5, decode_fin            /* Check if input is finished */

    ldrb w6, [x2, #1]             /* Load next character (count) */

    cmp w6, #'9'                  /* Check if count is digit */
    b.le decode_digit             /* Count is digit -> branch to handle */

    sub w6, w6, #'a'              /* Count is not digit -> convert char to digit */
    add w6, w6, #10
    b repeat_loop                 /* Count is converted -> begin decoding */

/* Converts ASCII ints to a digit. */
decode_digit:
    sub w6, w6, #'0'              /* Convert ASCII -> digit */

/* Continually stores characters in output buffer depending on the char count */
repeat_loop:
    strb w5, [x3], #1             /* Store character -> increment pointer */
    add w4, w4, #1                /* Increment bytes written count */

    subs w6, w6, #1               /* Decrement char counter */
    b.gt repeat_loop              /* If count > 0 -> repeat loop */

    add x2, x2, #2                /* Count = 0 -> move input pointer */
    b decode_loop                 /* Move back to decoding */

decode_fin:
    mov w0, w4                    /* Store byte count in output register */
    ldp x29, x30, [sp], #16       /* Restore stack */

    ret


/** The _encode subroutine receives (via the C calling convention):
    **     arg1: address of a buffer containing an ascii message
    **     arg2: address of a buffer to write the output
    ** and it should:
    **     1. encode the message with rlc and write the result to the output buffer
    **     2. return the number of bytes that were written to the buffer
    ** also, whenever your subroutine returns, it must have preserved:
    **     1. the stack pointer (no extra pushes or pops)
    **     2. the frame pointer (x29)
    **     3. all non-volatile registers (x19 ~ x28)
    **     4. the contents of the input buffer
**/
_encode:
    /* Set up stack */
    stp x29, x30, [sp, #-16]!
    mov x29, sp

    ldr x2, [sp, #16]             /* Input pointer */
    ldr x3, [sp, #24]             /* Output pointer */
    mov x4, #0                    /* Total bytes written */

/* Loop responsible for encoding a series of one character. */
encode_loop:
    ldrb w5, [x2]                 /* Load one char into buffer */
    cbz w5, encode_fin            /* Check if input is finished */

    mov w6, #1                    /* Increase char count */

/* Loop responsible for counting the number of letters in a series. */
count_loop:
    ldrb w7, [x2, #1]             /* Peek next char */
    cmp w7, w5                    /* Check if current and next char are the same */
    b.ne write_pair               /* Different letters -> finish writing current char */

    add w6, w6, #1                /* Chars were same -> increase counter */
    cmp w6, #35                   /* Check count for over-length series */

    add x2, x2, #1                /* Move input pointer forward */
    b.eq write_pair               /* Finish writing current char if too long */
    b count_loop                  /* Continue counting */

/* Counting has finished for a letter -> write letter + count to output. */
write_pair:
    strb w5, [x3], #1             /* Store current char in output buffer -> move forward */
    add w4, w4, #1                /* Increase byte count */

    cmp w6, #10                   /* Check if count is letter or digit */
    b.lt encode_digit             /* Count is digit -> move to encode */

    add w6, w6, #'a' - 10         /* Convert to count to a-z */
    b encode_store                /* Count is set -> store count in output */

/* Encodes a digit as its ASCII equivalent. */
encode_digit:
    add w6, w6, #'0'              /* Convert digit to ASCII */

/* Stores encoded count in output, increase pointers and counters. */
encode_store:
    strb w6, [x3], #1             /* Store count into output buffer -> point next */
    add w4, w4, #1                /* Increase byte count */

    add x2, x2, #1                /* Move input pointer */
    b encode_loop                 /* Return to encode next character */

/* Encoding for string has finished -> set output and restore stack. */
encode_fin:
    mov w0, w4                    /* Store byte count in return register */

    ldp x29, x30, [sp], #16       /* Restore stack */
    ret
