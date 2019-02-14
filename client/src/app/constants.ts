export class Constants {
  public static get baseURL(): string {
    return "http://localhost:8082/";
  }

  public static get userApiURL(): string {
    return this.baseURL + "api/user/";
  }
}
