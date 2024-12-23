export interface IResult {
    _id: string;
    email: string;
    name: string;
    iq: number;
    yearOfBirth: string;
    educationType: string;
    educationLevel: string;
    createdAt: Date;
    updatedAt: Date;
    __v: number;
}

export interface IResultResponse {
    message: string;
    data: IResult;
}
