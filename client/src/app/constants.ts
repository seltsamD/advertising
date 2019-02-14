export class Constants {
  public static get baseURL(): string {
    return "http://localhost:8082/";
  }

  public static get userApiURL(): string {
    return this.baseURL + "api/user/";
  }

  public static APP_TYPES = [
    "IOS", "ANDROID", "WEBSITE"
  ];
  public static CONTENT_TYPES = [
    "VIDEO", "IMAGE", "HTML"
  ]

  public static get appApiURL(): string {
    return this.baseURL + "api/app/";
  }

  public static get tokenPart(): string {
    return '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
  }
}
