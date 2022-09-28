import JSEncrypt from 'jsencrypt'

/**
 * RSA加密
 * @param value 要加密的数据
 * @param rsaPublicKey RSA公钥
 */
export function encrypt(value: string,rsaPublicKey: string): string {
    const encrypt = new JSEncrypt()
    encrypt.setPublicKey(rsaPublicKey)
    const strEncrypt = encrypt.encrypt(value)
    if (strEncrypt === false){
        throw new Error('加密错误')
    }
    return strEncrypt
}

/**
 * RSA解密
 * @param value 要解密的数据
 * @param rsaPrivateKey RSA密钥
 */
export function decrypt(value: string,rsaPrivateKey: string):string {
    const decrypt = new JSEncrypt()
    decrypt.setPrivateKey(rsaPrivateKey)
    const strDecrypt = decrypt.decrypt(value)
    if (strDecrypt === false){
        throw new Error('解密错误')
    }
    return strDecrypt
}